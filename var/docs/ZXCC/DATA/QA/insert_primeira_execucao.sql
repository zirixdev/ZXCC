USE ZX_CC_QA;

INSERT INTO `VENDEDOR` (TIPO, NOME, NOME_FANTASIA, SITE, DATA_NASCIMENTO, DATA_INGRESSO, DELETED) VALUES (0,N'ZIRIX SOLUÇÕES EM RASTREAMENTO','ZIRIX','www.zirix.com.br','1899-12-30','2014-01-30',0);
INSERT INTO `VEICULO_MARCA` (NOME_MARCA, DELETED) VALUES ('VOLKSWAGEM',0),('FORD',0),('FIAT',0),('HONDA',0),('ASIAMOTORS',0),('AUDI',0),('BMW',0),('CHEVROLET',0),('CITROEN',0),('DODGE',0),('JEEP',0),('KAWASAKI',0),('KIAMOTORS',0),('LANDROVER',0),('MERCEDEZBENZ',0),('MITSUBISHI',0),('NISSAN',0),('PEUGEOT',0),('RENAULT',0),('SEAT',0),('SUZUKI',0),('TOYOTA',0),('TROLLER',0),('YAMAHA',0),('SCANIA',0),('AGRALLE',0),('RANDON',0),('HYUNDAI',0),('OUTRO',0);
INSERT INTO `VEICULO_COMBUSTIVEL` (NOME_COMBUSTIVEL, DELETED) VALUES ('DIESEL',0),('ETANOL',0),('GASOLINA',0),('GNV',0),('OUTRO',0);
INSERT INTO `TIPO_UNIDADE` (NOME, DELETED) VALUES (N'Estoque',0),(N'Veículo',0);
INSERT INTO `TIPO_ENDERECO` (NOME, DELETED) VALUES (N'Correspondência',0),('Cadastro',0),('Outro',0);
INSERT INTO `TIPO_DOCUMENTO` (NOME, DELETED) VALUES ('RG',0),('CPF',0),('CNPJ',0),('CIC',0),('OUTROS',0),(N'INSCRIÇÃO ESTADUAL',0),(N'INSCRIÇÃO MUNICIPAL',0);
INSERT INTO `TIPO_CONTATO` (NOME_TIPO, DELETED) VALUES ('Residencial',0),('Comercial',0),('Celular',0),('Recado',0),('Fax',0),(N'Rádio',0),('Outro',0);
INSERT INTO `TELA` (NOME_TELA, DELETED) VALUES ('menu_opr',0),('menu_opr_cad',0),('menu_opr_cad_cli',0),('menu_opr_cad_eqp',0),('menu_opr_cad_chp',0),('menu_opr_con',0),('menu_adm',0),('menu_adm_cad',0),('menu_adm_cad_ven',0),('menu_adm_con',0),('menu_com',0),('menu_com_cad_ped',0),('menu_rel',0),('menu_cfg',0),('menu_sos',0),('menu_ext',0),('menu_com_con',0),('menu_com_cad',0),('menu_com_cad_cli',0);
INSERT INTO `STATUS_CHIP` (NOME_STATUS, DELETED) VALUES ('ATIVO',0),('INATIVO',0),(N'MANUTENÇÃO',0),('OUTRO',0);
INSERT INTO `PAIS` (NOME_PAIS, DELETED) VALUES (N'Afeganistão',0),(N'África do Sul',0),(N'Akrotiri',0),(N'Albânia',0),(N'Alemanha',0),(N'Andorra',0),(N'Angola',0),(N'Anguila',0),(N'Antárctida',0),(N'Antígua e Barbuda',0),(N'Antilhas Neerlandesas',0),(N'Arábia Saudita',0),(N'Arctic Ocean',0),(N'Argélia',0),(N'Argentina',0),(N'Arménia',0),(N'Aruba',0),(N'Ashmore and Cartier Islands',0),(N'Atlantic Ocean',0),(N'Austrália',0),(N'Áustria',0),(N'Azerbaijão',0),(N'Baamas',0),(N'Bangladeche',0),(N'Barbados',0),(N'Barém',0),(N'Bélgica',0),(N'Belize',0),(N'Benim',0),(N'Bermudas',0),(N'Bielorrússia',0),(N'Birmânia',0),(N'Bolívia',0),(N'Bósnia e Herzegovina',0),(N'Botsuana',0),(N'Brasil',0),(N'Brunei',0),(N'Bulgária',0),(N'Burquina Faso',0),(N'Burúndi',0),(N'Butão',0),(N'Cabo Verde',0),(N'Camarões',0),(N'Camboja',0),(N'Canadá',0),(N'Catar',0),(N'Cazaquistão',0),(N'Chade',0),(N'Chile',0),(N'China',0),(N'Chipre',0),(N'Clipperton Island',0),(N'Colômbia',0),(N'Comores',0),(N'Congo-Brazzaville',0),(N'Congo-Kinshasa',0),(N'Coral Sea Islands',0),(N'Coreia do Norte',0),(N'Coreia do Sul',0),(N'Costa do Marfim',0),(N'Costa Rica',0),(N'Croácia',0),(N'Cuba',0),(N'Dhekelia',0),(N'Dinamarca',0),(N'Domínica',0),(N'Egipto',0),(N'Emiratos Árabes Unidos',0),(N'Equador',0),(N'Eritreia',0),(N'Eslováquia',0),(N'Eslovénia',0),(N'Espanha',0),(N'Estados Unidos',0),(N'Estónia',0),(N'Etiópia',0),(N'Faroé',0),(N'Fiji',0),(N'Filipinas',0),(N'Finlândia',0),(N'França',0),(N'Gabão',0),(N'Gâmbia',0),(N'Gana',0),(N'Gaza Strip',0),(N'Geórgia',0),(N'Geórgia do Sul e Sandwich do Sul',0),(N'Gibraltar',0),(N'Granada',0),(N'Grécia',0),(N'Gronelândia',0),(N'Guame',0),(N'Guatemala',0),(N'Guernsey',0),(N'Guiana',0),(N'Guiné',0),(N'Guiné Equatorial',0),(N'Guiné-Bissau',0),(N'Haiti',0),(N'Honduras',0),(N'Hong Kong',0),(N'Hungria',0),(N'Iémen',0),(N'Ilha Bouvet',0),(N'Ilha do Natal',0),(N'Ilha Norfolk',0),(N'Ilhas Caimão',0),(N'Ilhas Cook',0),(N'Ilhas dos Cocos',0),(N'Ilhas Falkland',0),(N'Ilhas Heard e McDonald',0),(N'Ilhas Marshall',0),(N'Ilhas Salomão',0),(N'Ilhas Turcas e Caicos',0),(N'Ilhas Virgens Americanas',0),(N'Ilhas Virgens Britânicas',0),(N'Índia',0),(N'Indian Ocean',0),(N'Indonésia',0),(N'Irão',0),(N'Iraque',0),(N'Irlanda',0),(N'Islândia',0),(N'Israel',0),(N'Itália',0),(N'Jamaica',0),(N'Jan Mayen',0),(N'Japão',0),(N'Jersey',0),(N'Jibuti',0),(N'Jordânia',0),(N'Kuwait',0),(N'Laos',0),(N'Lesoto',0),(N'Letónia',0),(N'Líbano',0),(N'Libéria',0),(N'Líbia',0),(N'Listenstaine',0),(N'Lituânia',0),(N'Luxemburgo',0),(N'Macau',0),(N'Macedónia',0),(N'Madagáscar',0),(N'Malásia',0),(N'Malávi',0),(N'Maldivas',0),(N'Mali',0),(N'Malta',0),(N'Man, Isle of',0),(N'Marianas do Norte',0),(N'Marrocos',0),(N'Maurícia',0),(N'Mauritânia',0),(N'Mayotte',0),(N'México',0),(N'Micronésia',0),(N'Moçambique',0),(N'Moldávia',0),(N'Mónaco',0),(N'Mongólia',0),(N'Monserrate',0),(N'Montenegro',0),(N'Mundo',0),(N'Namíbia',0),(N'Nauru',0),(N'Navassa Island',0),(N'Nepal',0),(N'Nicarágua',0),(N'Níger',0),(N'Nigéria',0),(N'Niue',0),(N'Noruega',0),(N'Nova Caledónia',0),(N'Nova Zelândia',0),(N'Omã',0),(N'Pacific Ocean',0),(N'Países Baixos',0),(N'Palau',0),(N'Panamá',0),(N'Papua-Nova Guiné',0),(N'Paquistão',0),(N'Paracel Islands',0),(N'Paraguai',0),(N'Peru',0),(N'Pitcairn',0),(N'Polinésia Francesa',0),(N'Polónia',0),(N'Porto Rico',0),(N'Portugal',0),(N'Quénia',0),(N'Quirguizistão',0),(N'Quiribáti',0),(N'Reino Unido',0),(N'República Centro-Africana',0),(N'República Checa',0),(N'República Dominicana',0),(N'Roménia',0),(N'Ruanda',0),(N'Rússia',0),(N'Salvador',0),(N'Samoa',0),(N'Samoa Americana',0),(N'Santa Helena',0),(N'Santa Lúcia',0),(N'São Cristóvão e Neves',0),(N'São Marinho',0),(N'São Pedro e Miquelon',0),(N'São Tomé e Príncipe',0),(N'São Vicente e Granadinas',0),(N'Sara Ocidental',0),(N'Seicheles',0),(N'Senegal',0),(N'Serra Leoa',0),(N'Sérvia',0),(N'Singapura',0),(N'Síria',0),(N'Somália',0),(N'Southern Ocean',0),(N'Spratly Islands',0),(N'Sri Lanca',0),(N'Suazilândia',0),(N'Sudão',0),(N'Suécia',0),(N'Suíça',0),(N'Suriname',0),(N'Svalbard e Jan Mayen',0),(N'Tailândia',0),(N'Taiwan',0),(N'Tajiquistão',0),(N'Tanzânia',0),(N'Território Britânico do Oceano Índico',0),(N'Territórios Austrais Franceses',0),(N'Timor Leste',0),(N'Togo',0),(N'Tokelau',0),(N'Tonga',0),(N'Trindade e Tobago',0),(N'Tunísia',0),(N'Turquemenistão',0),(N'Turquia',0),(N'Tuvalu',0),(N'Ucrânia',0),(N'Uganda',0),(N'União Europeia',0),(N'Uruguai',0),(N'Usbequistão',0),(N'Vanuatu',0),(N'Vaticano',0),(N'Venezuela',0),(N'Vietname',0),(N'Wake Island',0),(N'Wallis e Futuna',0),(N'West Bank',0),(N'Zâmbia',0),(N'Zimbabué',0);
INSERT INTO `OPERADORA_CHIP` (NOME_OPERADORA, DELETED) VALUES ('CLARO',0),('NEXTEL',0),('OI',0),('TIM',0),('VIVO',0),('OUTRO',0);
INSERT INTO `MOTIVO_HIST_MODULO` (NOME, DELETED) VALUES (N'SAÍDA DE ESTOQUE',0),(N'DESINSTALAÇÃO',0),(N'RETIRADA PARA MANUTENÇÃO',0),(N'MÓDULO COM DEFEITO',0);
INSERT INTO `MARCA_MODULO` (NOME_MARCA, DELETED) VALUES ('ABSOLUT',0),('CALAMP',0),('CONTINENTAL',0),('FUL-MAR',0),('GENO MACHINES',0),('HOUDER',0),('KARITEC',0),('MAGNETI MARELLI',0),('MAXTRACK',0),('NASTEK',0),('QUANTA',0),('RAJATRACK',0),('SANAV',0),('SKYPATROL',0),('SMART CAR',0),('SMART GPS',0),('SUNTECH',0),('SVIAS',0),('VIRTEC',0),('OUTROS',0);
INSERT INTO `INFO_CONTATO` (NOME_GRAU, DELETED) VALUES (N'Próprio',0),(N'Pai / Mãe',0),('Recado',0);
INSERT INTO `GRUPO` (NOME_GRUPO, GRUPO, DELETED) VALUES ('ADM_SISTEMA','ADMIN',0),('ADMINISTRACAO','ADM',0),('OPERACIONAL','OPR',0),('COMERCIAL','COM',0),('FINANCEIRO','FIN',0),('DIRETORIA','DIR',0);
INSERT INTO `ESTADO_MODULO` (NOME_ESTADO, DELETED) VALUES ('ATIVO',0),(N'MANUTENÇÃO',0),('INATIVO',0);
