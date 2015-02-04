/*ZIRIX CONTROL CENTER - PACOTE CHIP DAO SERVICE
DESENVOLVIDO POR ZIRIX SOLUCOES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PacoteChipDAOService {
	public static Vector<PacoteChipDAO> loadAll() throws SQLException {

		String query = "SELECT * FROM " + PacoteChipDAO.TABLENAME;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		Vector<PacoteChipDAO> daoList = new Vector<PacoteChipDAO>();
		try {
			con = DAOManager.getInstance().getConnection();
			stmt = con.prepareStatement(query);
			res = stmt.executeQuery();

			while (res.next()) {
				int COD_PACOTE = res.getInt("COD_PACOTE");
				PkList pkList = new PkList();
				pkList.put("COD_PACOTE",COD_PACOTE);
				PacoteChipDAO dao = new PacoteChipDAO(pkList);

				dao.read();
				dao.loadAttsFromResultSet(res);
				daoList.add(dao);
			}
			return daoList;
		}catch(SQLException e){ throw e; }
        finally {
        	if (res != null) res.close();
        	if (stmt != null) stmt.close();
        	DAOManager.getInstance().closeConnection(con);
        }
	}
}
