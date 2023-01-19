package com.employeeapp.mapper;

public interface IMapper <I, O>{

    public O map(I in);
}
