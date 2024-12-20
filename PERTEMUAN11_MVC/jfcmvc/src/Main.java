import model.MyBatisUtil;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import view.UserView;
import controller.UserController;

public class Main {
    public static void main(String[] args) {
        // Membuat instance dari UserView
        UserView view = new UserView();

        // Membuat controller dan menghubungkan View dengan logika
        new UserController(view);

        // Menampilkan View
        view.setVisible(true);
    }
}
