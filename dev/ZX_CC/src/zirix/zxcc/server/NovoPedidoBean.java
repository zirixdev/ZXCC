/*ZIRIX CONTROL CENTER - CHIP SERVICE BEAN
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

import java.sql.SQLException;

import zirix.zxcc.server.dao.*;

public class NovoPedidoBean {

	private PedidoDAO dao_ = null;
	private Integer COD_PEDIDO_ = null;

	public NovoPedidoBean(String[] pkVal) {
		setPk(pkVal);
	}

	public NovoPedidoBean() {

	}

	public void setPk(Object[] pkVal) {

		COD_PEDIDO_ = new Integer((String)pkVal[0]);

		PkList pkList = new PkList();
	    pkList.put("COD_PEDIDO",COD_PEDIDO_);
	    dao_ = new PedidoDAO(pkList);

	    try {
	    	dao_.read();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {}
	}

	public String getNfe() {
    	String nfe = (String)dao_.getAttValueFor("NFE");	    	
    	return nfe;
	}

	public Integer getCodOperadora(){
		Integer codOperadora = (Integer)dao_.getAttValueFor("COD_OPERADORA");
		return codOperadora;
	}
}