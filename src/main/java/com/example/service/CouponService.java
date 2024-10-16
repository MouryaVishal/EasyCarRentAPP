package com.example.service;


import com.example.exception.couponException.CouponNotFoundException;
import com.example.exception.customerException.CustomerNotFoundException;
import com.example.model.Coupon;
import com.example.model.Customer;
import com.example.repo.CouponRepository;
import com.example.repo.CustomerRepository;
import com.example.request.CouponResquestToAdd;
import com.example.service.servicesInterface.CouponServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//! All operation related to Coupon service is done


@Service
public class CouponService implements CouponServiceInterface {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CustomerRepository customerRepository;


//    * adding new coupon to space by providing CouponResquestToAdd object
    public ResponseEntity<Object> addCoupon(CouponResquestToAdd coupon){
        Optional<Coupon> isCouponPresent=couponRepository.findByName(coupon.getCouponName());
        if(isCouponPresent.isPresent()){
            return  new ResponseEntity<>("Sorry! Coupon with name:"+coupon.getCouponName()+" already present!!",HttpStatus.CONFLICT);
        }
        Coupon addedCoupon=new Coupon();
        addedCoupon.setName(coupon.getCouponName());
        addedCoupon.setDiscountValue(coupon.getDiscountAmount());
        couponRepository.save(addedCoupon);
        return new ResponseEntity<>(addedCoupon,HttpStatus.OK);
    }

//    * fetching all coupon from DB to show
    public Iterable<Coupon> allCoupon(){
        return couponRepository.findAll();
    }

//    * deleting coupon from DB by providing Coupon ID
    public String deleteById(Long id){
        if(couponRepository.existsById(id)) {
            couponRepository.deleteById(id);
            return "Deleted SuccessFully!!";
        };
        return "Delete Fail. No Such id found!!";
    }

//    * updating coupon by providing coupon id,CouponResquestToAdd object which need to update
    public ResponseEntity<Object> updateById(Long id, CouponResquestToAdd coupon) {
        String couponName= coupon.getCouponName();
        Optional<Coupon> checkingCouponAllreadyPresent=couponRepository.findByName(couponName);
        if(checkingCouponAllreadyPresent.isPresent()){
            return new ResponseEntity<>(couponName+" is ready present in Space...", HttpStatus.CONFLICT);
        }
        Optional<Coupon> updateCoupon = couponRepository.findById(id);
        if (updateCoupon.isPresent()) {

            updateCoupon.get().setName(coupon.getCouponName());
            updateCoupon.get().setDiscountValue(coupon.getDiscountAmount());
            Coupon updatedCat = couponRepository.save(updateCoupon.get());
            return new ResponseEntity<>(updatedCat, HttpStatus.OK);
        } else {
            throw new CouponNotFoundException();
        }
    }

//    *Finding coupon which a customer can used by providing customer ID and number of days for rent a car
    public ResponseEntity<List<Object>> findCouponForCustomer(Long id, int days ){
        List<Object> coupons = new ArrayList<>();
        if(days>30) {
            coupons.add("NO Coupon Available for more than 30days....");
            return new ResponseEntity<>(coupons,HttpStatus.NOT_FOUND);
        }
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw  new CustomerNotFoundException();
        }
        String couponName="";
        if(customer.isEmpty()){
            couponName="FIRSTTIME";
        }
        if (days>=20){
            couponName="Coupon30";
        }else if (days>=10){
            couponName="Coupon20";
        }else {
            couponName="Coupon10";
        }
        Optional<Coupon> findCouponInSpace=couponRepository.findByName(couponName);
        if(findCouponInSpace.isEmpty()){
            throw new CouponNotFoundException();
        }
        coupons.add(couponName);
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }
}
