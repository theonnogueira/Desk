package com.theo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theo.helpdesk.domain.People;

public interface PeopleRepository extends JpaRepository<People, Integer>{

}
