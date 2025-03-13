package com.example.lab4.repo;

import com.example.lab4.models.Borrower;
import org.springframework.data.repository.CrudRepository;

public interface BorrowerRepository extends CrudRepository<Borrower, Long> {
}
