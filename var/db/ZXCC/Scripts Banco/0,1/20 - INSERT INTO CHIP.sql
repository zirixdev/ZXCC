INSERT INTO CHIP
(NFE, ICCID, COD_OPERADORA, APN, TECNOLOGIA, COD_STATUS, DDD, NUMERO_CHIP)
  select
		 ISNULL(str(nfe_chip), 'Nao Info'),
	     ISNULL(ICCID, 'Nao Info'),
		 CASE UPPER(OPERADORA)
		   WHEN 'CLARO' THEN '1'
		   WHEN 'NEXTEL' THEN '2'
		   WHEN 'OI' THEN '3'
		   WHEN 'VIVO' THEN '4'
		   WHEN 'TIM' THEN '5'
		   ELSE '6'
		 END,
	     ISNULL(apn, 'Nao Info'),
	     ISNULL(tec, 'Nao Info'),
	     1,
	     ISNULL(SUBSTRING(str(numero_telefone),1,2), '00'),
	     ISNULL(SUBSTRING(str(numero_telefone),3,9), '00000000')
	from ZX_CC_DEV.DBO.ESTOQUESQL;