package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class DocumentoVendedorDAO extends GenericDAO<DocumentoVendedorDAO> {

    public DocumentoVendedorDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public DocumentoVendedorDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {

    	setAttValueFor("COD_VENDEDOR",res.getInt("COD_VENDEDOR"));
    	setAttValueFor("COD_DOCUMENTO",res.getInt("COD_DOCUMENTO"));
    	setAttValueFor("NUMERO",res.getString("NUMERO"));
    	setAttValueFor("DATA_EMISSAO",res.getDate("DATA_EMISSAO"));
    	setAttValueFor("ORGAO_EMISSOR",res.getString("ORGAO_EMISSOR")); 	        	    	   
    }
    
    public Set<String> getPkNamesSet() {    	
    	return DocumentoVendedorDAO.createKey("COD_DOCUMENTO_VEN", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

    public final static String TABLENAME = "ZX_CC_DEV.dbo.DOCUMENTO_VENDEDOR";

}