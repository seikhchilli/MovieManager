package com.example.MovieManager.repository.movie;

import com.example.MovieManager.entity.movie.TitleBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TitleBasicRepository extends JpaRepository<TitleBasic, String>, JpaSpecificationExecutor<TitleBasic> {}
