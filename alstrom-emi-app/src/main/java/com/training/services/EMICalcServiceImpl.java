package com.training.services;

import org.springframework.stereotype.Service;

@Service
public class EMICalcServiceImpl implements EMICalcService {

	@Override
	public double calculateEMI(double principle, double interest, double duration) {
		return (principle*interest)/(100*duration);
	}
}
