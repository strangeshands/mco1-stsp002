package com.mco1.TripLang.repository;

import com.mco1.TripLang.entity.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripMemberRepository
        extends JpaRepository<TripMember, Long> {
}
