package DAO;

/**
 *
 * @author Adm
 */
import Main.ProdutosDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//ATENÇÃO!! extende o conectaDAO
public class ProdutosDAO extends conectaDAO {

    public int venderProduto(Integer id) {
        int status;
        try {
            st = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");
            st.setInt(1, id);
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }

    //vai inserir itens na tabela produtos, deve receber um objeto do tipo produto
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

    //seleciona e retorna a lista de produtos
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
