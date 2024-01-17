//package com.brahmin.community.info.datafeeder;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface RegistrantRepository extends JpaRepository<Registrant, Long> {
//    // You can add custom query methods if needed
//
//    public List<Registrant> findByFirstNameContainingIgnoreCase(String firstName);
//
//    public List<Registrant> findByAddressContainingIgnoreCase(String firstName);
//
//    public List<Registrant> findByLastNameContainingIgnoreCase(String lastName);
//
//    public List<Registrant> findByOccupationContainingIgnoreCase(String designation);
//
//    public List<Registrant> findByPhoneNumber(long phoneNumber);
//
//    public List<Registrant> findByFirstNameContainingOrLastNameContainingOrOccupationContainingOrPhoneNumber(
//            String firstName, String lastName,String designation, long phoneNumber);
//
//    List<Registrant> findByGothramContainingIgnoreCase(String gothram);
//
//    List<Registrant> findBySetContainingIgnoreCase(String set);
//
//    List<Registrant> findByMarriedContainingIgnoreCase(String married);
//}
