CREATE TABLE `billing` (
  `id` int(10) NOT NULL,
  `billingtype` varchar(45) NOT NULL,
  `account_number` varchar(45) NOT NULL,
  `totalprice` varchar(100) NOT NULL,
  `remark` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `billing` (`id`, `billingtype`, `account_number`, `totalprice`, `remark`, `date`) VALUES
(1, 'domestic', '1234567', '1200', 'biller-mary', '2022-04-22'),
(2, 'business', '12345672', '12200', 'biller-alice', '2022-02-22');

ALTER TABLE `billing`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `billing`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;


  
 