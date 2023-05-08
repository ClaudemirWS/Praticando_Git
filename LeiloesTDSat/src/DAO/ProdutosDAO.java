package DAO;

/**
 *
 * @author Adm
 */
import Main.ProdutosDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO extends conectaDAO {

    public int cadastrarProduto(ProdutosDTO produto) {

        int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)");
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public List<ProdutosDTO> listarProdutos() {

        try {
            st = conn.prepareStatement("SELECT id, nome, valor, status FROM produtos");
            rs = st.executeQuery();
            List<ProdutosDTO> lista = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                lista.add(produto);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }

    }

}
