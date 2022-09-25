package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    @Query(value = "select * from verification_codes where extract(epoch from ( current_timestamp  - created_date))<300 and phone=:phone limit 1", nativeQuery = true)
    public VerificationCode alreadySend(String phone);

    @Query(value = "select * from verification_codes where phone=:phone and code=:code order by id desc limit 1", nativeQuery = true)
    public VerificationCode getByPhoneAndCode(String phone, String code);

}
