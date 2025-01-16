package services;

import dto.PostoRequest;
import entity.Posto;
import repositories.PostoRepository;

import java.sql.SQLException;
import java.util.List;

public class PostoService {
    public Posto getPostoById(Integer id) throws SQLException {
        return PostoRepository.getById(id);
    }

    public List<Posto> getAllPosto() throws SQLException {
        return PostoRepository.getAll();
    }

    public void insertPosto(PostoRequest request) throws SQLException {
        PostoRepository.insertPosto(request);
    }

    public void updatePosto(Integer id, PostoRequest request) throws SQLException {
        PostoRepository.updatePosto(id, request);
    }

    public void deletePostoById(Integer id) throws SQLException {
        PostoRepository.deleteById(id);
    }
}
