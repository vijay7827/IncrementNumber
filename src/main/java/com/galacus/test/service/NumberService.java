package com.galacus.test.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.galacus.test.dao.NumbersDao;
import com.galacus.test.exception.CustomException;
import com.galacus.test.model.Numbers;
import com.galacus.test.repository.NumberRepository;

@Service
public class NumberService {

	@Autowired
	private NumberRepository numberRepository;

	ReentrantLock lock = new ReentrantLock();

	public void addNewNumber() {
		NumbersDao numb = NumbersDao.builder().number(0).build();
		numberRepository.save(numb);
	}

	public Numbers increaseExistingNumberById(Long id) {
		Optional<NumbersDao> number = numberRepository.findById(id);
		if (!number.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name());
		}
		lock.lock();
		try {
			int count = number.get().getNumber();
			count++;
			number.get().setNumber(count);
			numberRepository.save(number.get());
		} finally {
			lock.unlock();
		}
		Numbers numberDto = new Numbers();
		BeanUtils.copyProperties(number.get(), numberDto);
		return numberDto;
	}
}
