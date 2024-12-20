import model.MyBatisUtil;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;

import view.UserPdf;
import view.UserView;
import controller.UserController;

public class Main {
    public static void main(String[] args) {
        // Mendapatkan SqlSession dari MyBatisUtil
        SqlSession session = MyBatisUtil.getSqlSession();

        // Mendapatkan mapper dari session
        UserMapper mapper = session.getMapper(UserMapper.class);

        // Membuat instance dari UserPdf
        UserPdf pdf = new UserPdf();

        // Membuat instance dari UserView
        UserView view = new UserView();

        // Membuat instance dari UserController dan menghubungkan View, Mapper, dan Pdf
        new UserController(view, mapper, pdf);

        // Menampilkan jendela utama (UserView)
        view.setVisible(true);
    }
}
