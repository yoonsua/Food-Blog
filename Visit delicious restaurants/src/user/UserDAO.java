package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	//�����ͺ��̽��� ����, ���� �־��ִ� ��ü
	
	private Connection conn;
	private PreparedStatement pstmt; 
	private ResultSet rs;
	//�ܺ� ���̷���
	
	public UserDAO() {
		//mysql�� ����
		//UserDAO�� ������
		try {
			String dbURL ="jdbc:mysql://localhost:3306/BBS?autoReconnect=true&useSSL=false";
			//localhost�� ���� ��ǻ���� �ּҸ� �ǹ�, 3306��Ʈ�� ��Ƶ� mysql�� ���� bbs�� 3306������ db
			String dbID = "root";
			String dbPassword = "qweqaz156!";
			Class.forName("com.mysql.jdbc.Driver");
			//driver�� mysql�� ���� �� �� �ְ��ϴ� �ϳ��� ���̺귯��
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			//db�� ���� conn��ü �ȿ� db������ ��
		} catch(Exception e) {
			e.printStackTrace();
			//���� �߻��� ������ ������
		}
	}
	
	public int login(String userID, String userPassword) {
		//�α��� �۾�
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			//pstmt�� �����ͺ��̽� ������ �����ϴ� �ɷ� �ν��Ͻ��� ������
			pstmt.setString(1,userID);
			//�� sql����� ���� ��ŷ ����� �������� ?�� �ص״ٰ� �ٲٴ� �� userID�� ?�� ����
			rs=pstmt.executeQuery();
			//����� ���� �� �ִ� rs���ٰ� ������ ����� �־��ش�.
			if(rs.next()) {
				//��� �����ϸ�
				if(rs.getString(1).equals(userPassword)) {
					//���̵��� ��й�ȣ�� �Է��� ��й�ȣ�� ��
					return 1;//���̵� �ִ� => �α��� ����
				}
				else
					return 0;//��й�ȣ�� ���̵� ��ġ���� ����  => �α��� ����
			}
			return -1;//bbs db�� ���̵� ����
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;//�����ͺ��̽� ����
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (? ,? ,? ,? ,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5,user.getUserEmail());
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
}
