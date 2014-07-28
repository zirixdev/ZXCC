package zirix.zxcc.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import zirix.zxcc.server.dao.UsuarioDAO;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.PkList;


public class ZxAccessControlBean {

	private UsuarioDAO dao_ = null;
	private Integer COD_USUARIO_ = null;
	private Integer COD_GRUPO_ = null;

	public ZxAccessControlBean(String[] pkVal) {
		setPk(pkVal);
		try{
			String query = "SELECT ZX_CC_DEV.dbo.USUARIO.COD_GRUPO FROM ZX_CC_DEV.dbo.USUARIO WHERE COD_USUARIO=\'" + COD_USUARIO_ + "\'";
		    ArrayList<Object[]> values = DAOManager.getInstance().executeQuery(query);
		    Object[] retVal = (Object[])values.get(0);

		    if (((Integer)retVal[0]) != null){
		    	COD_GRUPO_ = ((Integer)retVal[0]);
		    }
		} catch (Exception ex) {
    		ex.printStackTrace();
		}  finally {
		}
	}

	public ZxAccessControlBean() {
	}

	public void setPk(Object[] pkVal) {

		COD_USUARIO_ = new Integer((String)pkVal[0]);

		PkList pkList = new PkList();
	    pkList.put("COD_USUARIO",COD_USUARIO_);
	    dao_ = new UsuarioDAO(pkList);

	    try {
	    	dao_.read();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    }
	}

	public String getNomeUsuario(){
		String nomeUsuario = (String)dao_.getAttValueFor("NOME_USUARIO");	    	
		return nomeUsuario;
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getPermissaoUsuario(){
		Vector<String[]> PermissaoUsuario = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT ZX_CC_DEV.dbo.PERMISSAO_USUARIO.COD_TELA "
					+ "                                                              , ZX_CC_DEV.dbo.PERMISSAO_USUARIO.CHAVE "
					+ "                                                           FROM ZX_CC_DEV.dbo.PERMISSAO_USUARIO "
					+ "                                                          WHERE ZX_CC_DEV.dbo.PERMISSAO_USUARIO.COD_USUARIO = " + COD_USUARIO_);

		    for (int i=0;i < values.size();i++) {
			    String[] attList = new String[2]; // pois eu sei que sao 2 atributos de fato !
			    attList[0] = values.get(i)[0].toString();
			    attList[1] = values.get(i)[1].toString();
			    PermissaoUsuario.add(attList);
		    }
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return PermissaoUsuario;
		}
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getPermissaoGrupo(){
		Vector<String[]> PermissaoGrupo = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT ZX_CC_DEV.dbo.PERMISSAO_GRUPO.COD_TELA "
					+ "                                                              , ZX_CC_DEV.dbo.PERMISSAO_GRUPO.CHAVE "
					+ "                                                           FROM ZX_CC_DEV.dbo.PERMISSAO_GRUPO "
					+ "                                                          WHERE ZX_CC_DEV.dbo.PERMISSAO_GRUPO.COD_GRUPO = " + COD_GRUPO_);

		    for (int i=0;i < values.size();i++) {
			    String[] attList = new String[2]; // pois eu sei que sao 2 atributos de fato !
			    attList[0] = values.get(i)[0].toString();
			    attList[1] = values.get(i)[1].toString();
			    PermissaoGrupo.add(attList);
		    }
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return PermissaoGrupo;
		}
	}
}
