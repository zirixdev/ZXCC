/*ZIRIX CONTROL CENTER - CHIP SERVICE BEAN
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import zirix.zxcc.server.dao.*;

public class NovoPedidoServiceBean {

	private PedidoDAO daoPedido_ = null;
	private Integer COD_PEDIDO_ = null;

	public NovoPedidoServiceBean(String[] pkVal) {
		setPk(pkVal);
	}

	public NovoPedidoServiceBean() {

	}

	public void setPk(Object[] pkVal) {

		COD_PEDIDO_ = new Integer((String)pkVal[0]);

		PkList pkList = new PkList();
	    pkList.put("COD_PEDIDO",COD_PEDIDO_);
	    daoPedido_ = new PedidoDAO(pkList);

	    try {
	    	daoPedido_.read();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {}
	}

	public String[] getCodCliente(){
		String[] codCliente = (String[])daoPedido_.getAttValueFor("COD_CLIENTE");
		return codCliente;
	}

	public Integer getDataVencimento() {
    	int dataVencimento = (Integer)daoPedido_.getAttValueFor("DATA_VENCIMENTO");
    	return dataVencimento;
	}

	public Integer getCodTipo() {
    	int codTipo = (Integer)daoPedido_.getAttValueFor("COD_TIPO");
    	return codTipo;
	}

	public Integer getNumeroPedido() {
    	int numeroPedido = (Integer)daoPedido_.getAttValueFor("NUM_PEDIDO");
    	return numeroPedido;
	}

}