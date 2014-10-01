USE [ZX_CC_DEV]
GO

/****** Object:  Table [dbo].[CLIENTES]    Script Date: 05/12/2014 14:29:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[CLIENTE](
	[COD_CLIENTE] [int] IDENTITY(1,1) NOT NULL,
	[TIPO] [int] NOT NULL,
	[NOME] [char](50) NOT NULL,
	[NOME_FANTASIA] [char] (50) NULL,
	[SITE] [char](60) NULL,
	[DATA_NASCIMENTO] [date] NOT NULL,
	[DATA_INGRESSO] [date] NOT NULL,
	[COD_VENDEDOR] [int] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[ENDERECO_CLIENTE](
	[COD_ENDERECO_CLI] [int] IDENTITY(1,1) NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[ENDERECO] [char](100) NOT NULL,
	[BAIRRO] [char](50) NOT NULL,
	[CIDADE] [char](50) NOT NULL,
	[UF] [char](2) NOT NULL,
	[COD_PAIS] [int] NOT NULL,
	[COMPLEMENTO] [char](30) NULL,
	[CEP] [char](9) NOT NULL,
	[COD_ENDERECO] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[PAIS](
	[COD_PAIS] [int] IDENTITY(1,1) NOT NULL,
	[NOME_PAIS] [char](50) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[TIPO_ENDERECO](
	[COD_ENDERECO] [int] IDENTITY(1,1) NOT NULL,
	[NOME] [char](50) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[CONTATO_CLIENTE](
	[COD_CONTATO_CLI] [int] IDENTITY(1,1) NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[COD_CONTATO] [int] NOT NULL,
	[DDD] [char](3) NOT NULL,
	[NUMERO] [char](10) NOT NULL,
	[COD_PAIS] [int] NULL,
	[NOME] [char] (50) NULL,
	[COD_GRAU] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[TIPO_CONTATO](
	[COD_CONTATO] [int] IDENTITY(1,1) NOT NULL,
	[NOME_TIPO] [char](50) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[DOCUMENTO_CLIENTE](
	[COD_DOCUMENTO_CLI] [int] IDENTITY(1,1) NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[COD_DOCUMENTO] [int] NOT NULL,
	[NUMERO] [char](20) NOT NULL,
	[DATA_EMISSAO] [date] NULL,
	[ORGAO_EMISSOR] [char] (20) NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[TIPO_DOCUMENTO](
	[COD_DOCUMENTO] [int] IDENTITY(1,1) NOT NULL,
	[NOME] [char](50) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[VENDEDOR](
	[COD_VENDEDOR] [int] IDENTITY(1,1) NOT NULL,
	[TIPO] [int] NOT NULL,
	[NOME] [char](50) NOT NULL,
	[NOME_FANTASIA] [char] (50) NULL,
	[SITE] [char](60) NULL,
	[DATA_NASCIMENTO] [date] NOT NULL,
	[DATA_INGRESSO] [date] NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[ENDERECO_VENDEDOR](
	[COD_ENDERECO_VEN] [int] IDENTITY(1,1) NOT NULL,
	[COD_VENDEDOR] [int] NOT NULL,
	[ENDERECO] [char](100) NOT NULL,
	[BAIRRO] [char](50) NOT NULL,
	[CIDADE] [char](50) NOT NULL,
	[UF] [char](2) NOT NULL,
	[COD_PAIS] [int] NOT NULL,
	[COMPLEMENTO] [char](30) NULL,
	[CEP] [char](9) NOT NULL,
	[COD_ENDERECO] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[CONTATO_VENDEDOR](
	[COD_CONTATO_VEN] [int] IDENTITY(1,1) NOT NULL,
	[COD_VENDEDOR] [int] NOT NULL,
	[COD_CONTATO] [int] NOT NULL,
	[DDD] [char](3) NOT NULL,
	[NUMERO] [char](10) NOT NULL,
	[COD_PAIS] [int] NULL,
	[NOME] [char] (50) NULL,
	[COD_GRAU] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[DOCUMENTO_VENDEDOR](
	[COD_DOCUMENTO_VEN] [int] IDENTITY(1,1) NOT NULL,
	[COD_VENDEDOR] [int] NOT NULL,
	[COD_DOCUMENTO] [int] NOT NULL,
	[NUMERO] [char](20) NOT NULL,
	[DATA_EMISSAO] [date] NULL,
	[ORGAO_EMISSOR] [char] (20) NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[VEICULO](
	[COD_VEICULO] [int] IDENTITY(1,1) NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[PLACA] [char] (8) NOT NULL,
	[ANO_FAB] [int] NOT NULL,
	[ANO_MOD] [int] NOT NULL,
	[COD_MARCA] [int] NOT NULL,
	[MODELO] [char] (50) NOT NULL,
	[COR] [char] (50) NOT NULL,
	[CHASSI] [char] (30) NOT NULL,
	[RENAVAN] [char] (20) NOT NULL,
	[DATA_INGRESSO] [date] NOT NULL,
	[COD_COMBUSTIVEL] [int] NOT NULL,
	[VOLT] [int] NOT NULL,
	[KM] [char] (20) NULL,
	[DATA_ULT_VISTORIA] [date] NOT NULL,
	[COD_INSTALACAO] [int] NULL,
	[COD_PEDIDO] [int] NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[VEICULO_MARCA](
	[COD_MARCA] [int] IDENTITY(1,1) NOT NULL,
	[NOME_MARCA] [char] (50) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[VEICULO_COMBUSTIVEL](
	[COD_COMBUSTIVEL] [int] IDENTITY(1,1) NOT NULL,
	[NOME_COMBUSTIVEL] [char] (50) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[INSTALACAO](
	[COD_INSTALACAO] [int] IDENTITY(1,1) NOT NULL,
	[COD_VEICULO] [int] NOT NULL,
	[COD_MODULO] [int] NOT NULL,
	[COD_UNIDADE] [int] NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[TIPO_UNIDADE](
	[COD_UNIDADE] [int] IDENTITY(1,1) NOT NULL,
	[NOME] [char](50) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[MODULO](
	[COD_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[NUMERO_MODULO] [char] (16) NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[COD_MODELO] [int] NOT NULL,
	[COD_CHIP] [int] NOT NULL,
	[NFE] [char] (20) NOT NULL,
	[COD_ESTADO] [int] NOT NULL,
	[COD_INSTALACAO] [int] NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[CHIP](
	[COD_CHIP] [int] IDENTITY(1,1) NOT NULL,
	[COD_MODULO] [int] NULL,
	[NFE] [char] (20) NOT NULL,
	[ICCID] [char] (25) NOT NULL,
	[COD_OPERADORA] [int] NOT NULL,
	[APN] [char] (50) NOT NULL,
	[TECNOLOGIA] [char] (10) NOT NULL,
	[COD_STATUS] [int] NOT NULL,
	[DDD] [char] (3) NOT NULL,
	[NUMERO_CHIP] [char] (15) NOT NULL,
	[DATA_VIGENCIA] [date] NOT NULL,
	[COD_CONTA] [int] NOT NULL,
	[COD_PACOTE] [int] NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[OPERADORA_CHIP](
	[COD_OPERADORA] [int] IDENTITY(1,1) NOT NULL,
	[NOME_OPERADORA] [char] (50) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[STATUS_CHIP](
	[COD_STATUS] [int] IDENTITY(1,1) NOT NULL,
	[NOME_STATUS] [char] (50) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[DESC_MODULO](
	[COD_MODELO] [int] IDENTITY(1,1) NOT NULL,
	[NOME_MODELO] [char] (50) NOT NULL,
	[COD_MARCA] [int] NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[MARCA_MODULO](
	[COD_MARCA] [int] IDENTITY(1,1) NOT NULL,
	[NOME_MARCA] [char] (50) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[ESTADO_MODULO](
	[COD_ESTADO] [int] IDENTITY(1,1) NOT NULL,
	[NOME_ESTADO] [char] (50) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[HIST_MODULO](
	[COD_HIST_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[COD_MODULO] [int] NOT NULL,
	[DATA_INGRESSO] [date] NOT NULL,
	[DATA_REMOVIDO] [date] NULL,
	[COD_INSTALACAO] [int] NOT NULL,
	[COD_MOTIVO] [int] NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[ESTOQUE](
	[COD_ESTOQUE] [int] IDENTITY(1,1) NOT NULL,
	[COD_INSTALACAO] [int] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[TELA](
	[COD_TELA] [int] IDENTITY(1,1) NOT NULL,
	[NOME_TELA] [char] (50) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[PERMISSAO_USUARIO](
	[COD_PERMISSAO] [int] IDENTITY(1,1) NOT NULL,
	[COD_USUARIO] [int] NOT NULL,
	[COD_TELA] [int] NOT NULL,
	[CHAVE] [int] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[USUARIO](
	[COD_USUARIO] [int] IDENTITY(1,1) NOT NULL,
	[NOME_USUARIO] [char] (50) NOT NULL,
	[LOGIN] [char] (20) NOT NULL,
	[SENHA] [char] (17) NOT NULL,
	[COD_GRUPO] [int] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[EMAIL_CLI_VEN](
	[COD_EMAIL] [int] IDENTITY(1,1) NOT NULL,
	[COD_CLI_VEN] [int] NOT NULL,
	[TIPO_CLI_VEN] [int] NOT NULL,
	[EMAIL] [char] (100) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[INFO_CONTATO](
	[COD_GRAU] [int] IDENTITY(1,1) NOT NULL,
	[NOME_GRAU] [char] (100) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[PEDIDO](
	[COD_PEDIDO] [int] IDENTITY(1,1) NOT NULL,
	[COD_VENDEDOR] [int] NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[NUM_PEDIDO] [int] NOT NULL,
	[COD_TIPO] [int] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[TIPO_PEDIDO](
	[COD_TIPO] [int] IDENTITY(1,1) NOT NULL,
	[NOME_TIPO] [char] (100) NOT NULL,
	[DESCRICAO] [char] (100) NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[OBS_PEDIDO](
	[COD_OBS] [int] IDENTITY(1,1) NOT NULL,
	[COD_PEDIDO] [int] NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[INDICE] [int] NOT NULL,
	[OBSERVACAO] [char] (200) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[ANEXO_PEDIDO](
	[COD_ANEXO] [int] IDENTITY(1,1) NOT NULL,
	[COD_PEDIDO] [int] NOT NULL,
	[COD_CLIENTE] [int] NOT NULL,
	[ANEXO] [char] (100) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[TIPO_SERVICO](
	[COD_SERVICO] [int] IDENTITY(1,1) NOT NULL,
	[NOME_SERVICO] [char] (100) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[SERVICO_PEDIDO](
	[COD_SERV_PED] [int] IDENTITY(1,1) NOT NULL,
	[COD_SERVICO] [int] NOT NULL,
	[COD_PEDIDO] [int] NOT NULL,
	[QUANTIDADE] [int] NOT NULL,
	[VALOR_UNITARIO] [decimal] (5,2) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[NUMERO_PEDIDO](
	[NUM_PEDIDO] [int] IDENTITY(1,1) NOT NULL,
	[COD_USUARIO] [int] NOT NULL,
	[DATA_GERACAO] [date] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[PERMISSAO_GRUPO](
	[COD_PERMISSAO] [int] IDENTITY(1,1) NOT NULL,
	[COD_GRUPO] [int] NOT NULL,
	[COD_TELA] [int] NOT NULL,
	[CHAVE] [int] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[GRUPO](
	[COD_GRUPO] [int] IDENTITY(1,1) NOT NULL,
	[NOME_GRUPO] [char] (50) NOT NULL,
	[GRUPO] [char] (6) NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[MOTIVO_HIST_MODULO](
	[COD_MOTIVO] [int] IDENTITY(1,1) NOT NULL,
	[NOME] [char](50) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[CONTA_CHIP](
	[COD_CONTA] [int] IDENTITY(1,1) NOT NULL,
	[NUMERO_CONTA] [char] (10) NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[PACOTE_CHIP](
	[COD_PACOTE] [int] IDENTITY(1,1) NOT NULL,
	[COD_CONTA] [int] NOT NULL,
	[NOME_PACOTE] [char] (25) NOT NULL,
	[INFO_PACOTE] [char] (100) NOT NULL,
	[COMPARTILHADO] [int] NOT NULL,
	[DELETED] [int] NOT NULL

) ON [PRIMARY]

GO

CREATE TABLE [dbo].[CLIENTE_PROSPECCAO](
	[COD_CLIENTE_PROSPECCAO] [int] IDENTITY(1,1) NOT NULL,
	[TIPO] [int] NOT NULL,
	[NOME] [char](50) NOT NULL,
	[NOME_FANTASIA] [char] (50) NULL,
	[DATA_INGRESSO] [date] NOT NULL,
	[COD_VENDEDOR] [int] NOT NULL,
	[DELETED] [int] NOT NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[CONTATO_PROSPECCAO](
	[COD_CONTATO_PROS] [int] IDENTITY(1,1) NOT NULL,
	[COD_CLIENTE_PROSPECCAO] [int] NOT NULL,
	[COD_CONTATO] [int] NOT NULL,
	[DDD] [char](3) NOT NULL,
	[NUMERO] [char](10) NOT NULL,
	[COD_PAIS] [int] NULL
	
) ON [PRIMARY]

GO

CREATE TABLE [dbo].[EMAIL_PROSPECCAO](
	[COD_EMAIL_PROSPECCAO] [int] IDENTITY(1,1) NOT NULL,
	[COD_CLIENTE_PROSPECCAO] [int] NOT NULL,
	[EMAIL] [char] (100) NOT NULL
	
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

