-- clear all fields that are unnecessary for the functionality of the program
delete from delivery; alter table delivery auto_increment = 0; select * from delivery;
delete from customer; alter table customer auto_increment = 0; select * from customer;
delete from adress; alter table adress auto_increment = 0; select * from adress;
delete from orderItem; alter table orderItem auto_increment = 0; select * from orderItem;
delete from `order`; alter table `order` auto_increment = 0; select * from `order`;
update discountcode set isused = 0;
update courier set status = "AVAILABLE"; update courier  set timelastdelivery = null;
