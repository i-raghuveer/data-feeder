package com.brahmin.community.info.datafeeder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrantRepository extends JpaRepository<Registrant, Long> {
    // You can add custom query methods if needed

    public List<Registrant> findByFirstNameContaining(String firstName);

    public List<Registrant> findByLastNameContaining(String lastName);

    public List<Registrant> findByDesignationContaining(String designation);

    public List<Registrant> findByPhoneNumber(long phoneNumber);

    public List<Registrant> findByFirstNameContainingOrLastNameContainingOrDesignationContainingOrPhoneNumber(
            String firstName, String lastName,String designation, long phoneNumber);
}
