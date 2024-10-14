package com.example.service;


import com.example.model.*;
import com.example.repo.CarRepository;
import com.example.repo.CouponRepository;
import com.example.repo.CustomerRepository;
import com.example.repo.RentalOrderRepository;

import com.example.request.RequsetRentalOrder;
import com.example.service.servicesInterface.RentalOrderServiceImterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class RentalOrderService implements RentalOrderServiceImterface {
    @Autowired
    private RentalOrderRepository rentalOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Transactional
    public ResponseEntity<Object> placeRentalOrder(RequsetRentalOrder request) {
        String customerName=request.getCustomerName();
        String carName=request.getCarName();
        List<String> couponNames=request.getCouponNames();
        int noOfDaysForRent=request.getNoOfDaysForRent();
        String carCategoryName=request.getCarCategoryName();
        String customerEmail=request.getCustomerEmail();

        // Fetch customer
        Optional<Customer> currCustomer=customerRepository.findByNameAndEmail(customerName,customerEmail);
//        System.out.println(currCustomer.get());
        if(currCustomer.isEmpty() || !Objects.equals(currCustomer.get().getEmail(), customerEmail)){
            return new ResponseEntity<>("Sorry! Customer not found...",HttpStatus.NOT_FOUND);
        }

        Optional<Car> car = carRepository.findByName(carName);
        if(car.isEmpty()){
            return new ResponseEntity<>("Sorry! car not found...",HttpStatus.NOT_FOUND);
        }
        if(car.get().getCountOfThatCar()<=0){
            return new ResponseEntity<>("Sorry! No instance of "+carName+" is available right now...",HttpStatus.NOT_FOUND);
        }
        if(!Objects.equals(car.get().getCategoryId().getCategoryName(), carCategoryName)){
            return new ResponseEntity<>("Sorry! "+carName+" with "+carCategoryName+" category is not present...",HttpStatus.NOT_FOUND);
        }

        List<Coupon> coupons = new ArrayList<>();
        for(String couponName:couponNames){
            if(Objects.equals(couponName, "string"))continue;
            System.out.println("Coupon");

            Optional<Coupon> coupon=couponRepository.findByName(couponName);
            if(coupon.isEmpty()){
                return new ResponseEntity<>("Sorry! "+couponName +" coupon is not provide by company...",HttpStatus.NOT_FOUND);
            }
            coupon.ifPresent(coupons::add);
        }

        // Validate rental days
        if (noOfDaysForRent <= 0 || noOfDaysForRent > 30) {
            return new ResponseEntity<>("Sorry! Rental Days can not be less then 0 and more the 30..."
                    ,HttpStatus.NOT_FOUND);
        }

        // Calculate total cost
        double totalCost = car.get().getPricePerDay() * noOfDaysForRent;
        List<Coupon> couponApplied=new ArrayList<>();
        if(currCustomer.get().getFistTime()){
            totalCost/=2D;
            Optional<Coupon> coupon=couponRepository.findByName("FIRSTTIME");
            couponApplied.add(coupon.get());
        }else{
            Double discountAmountAfterCouponApply = applyCouponDiscount(totalCost, coupons, noOfDaysForRent,couponApplied);
            totalCost-=discountAmountAfterCouponApply;
        }
        RentalOrder rentalOrder = new RentalOrder();
//        car.get().setIsAvailable(false);
        car.get().setCountOfThatCar(car.get().getCountOfThatCar()-1);
        currCustomer.get().setFistTime(false);
        rentalOrder.setOrderTotal(totalCost);
        rentalOrder.setCustomer(currCustomer.get());
        rentalOrder.setCars(car.get());
        rentalOrder.setRentalDays(noOfDaysForRent);
        rentalOrder.setCoupon(coupons);
        rentalOrder.setCoupon(couponApplied);
        rentalOrderRepository.save(rentalOrder);
        return new ResponseEntity<>(rentalOrder,HttpStatus.OK);
    }
    private double applyCouponDiscount(double totalCost, List<Coupon> coupons, int rentalDays,List<Coupon> appliedCoupons) {
        double discount = 0;
        Coupon appliedCoupon=null;
        if (rentalDays >= 5 && rentalDays < 10) {
            discount =Math.max(discount,totalCost * 0.1);
            appliedCoupon=couponRepository.findByName("Coupon10").get();
        } else if (rentalDays >= 10 && rentalDays < 30) {
            discount =Math.max(discount,totalCost * 0.2);
            appliedCoupon=couponRepository.findByName("Coupon20").get();
        } else if (rentalDays == 30) {
            discount =Math.max(discount,totalCost * 0.3);
            appliedCoupon=couponRepository.findByName("Coupon30").get();
        }
//        for (Coupon coupon : coupons){
//            Optional<Coupon> currCoupon=couponRepository.findById(coupon.getId());
//            String couponName="";
//            if(currCoupon.isPresent()){
//                couponName=currCoupon.get().getName();
//            }
//            discount = switch (couponName) {
//                case "Coupon10" -> Math.max(discount, totalCost * coupon.getDiscountValue() / 100);
//                case "Coupon30" -> Math.max(discount, totalCost * coupon.getDiscountValue() / 100);
//                case "Coupon20" -> Math.max(discount, totalCost * coupon.getDiscountValue() / 100);
//                case "Coupon50" -> Math.max(discount, totalCost * coupon.getDiscountValue() / 100);
//                default -> discount;
//            };
//        }
        appliedCoupons.add(appliedCoupon);
        return discount;
    }
    public Iterable<RentalOrder> allRentralOrder() {
        return rentalOrderRepository.findAll();
    }

    public String deleteById(Long id) {
        if (rentalOrderRepository.existsById(id)) {
            rentalOrderRepository.deleteById(id);
            return "Deleted SuccessFully!!";
        }
        return "Delete Fail. No Such id found!!";
    }

    public List<RentalOrder> allRentalOrderForParticularCustomer(Long id){
        Optional<Customer> customer=customerRepository.findById(id);
        List<RentalOrder> allOrder=rentalOrderRepository.findByCustomerId(customer.get().getId());
        return allOrder;
    }
}
