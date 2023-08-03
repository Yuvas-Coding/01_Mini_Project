package com.vtalent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtalent.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
