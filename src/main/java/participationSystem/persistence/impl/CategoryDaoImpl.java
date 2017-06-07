package participationSystem.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import participationSystem.model.Category;
import participationSystem.persistence.CategoryDao;
import persistence.Database;

public class CategoryDaoImpl implements CategoryDao{
	
	//Queries
	private String ADD_CATEGORY = "INSERT INTO categories (NAME) VALUES (?)";
	private String FIND_CATEGORY_BY_ID = "SELECT * FROM categories WHERE ID=?";
	private static String ALL_CATEGORIES = "SELECT * FROM categories";


	@Override
	public void addCategory(Category category) {
		
		PreparedStatement pst = null;
		Connection con = null;
		
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(ADD_CATEGORY);
			pst.setString(1, category.getCategoryName());

			pst.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public Category findCategoryById(int id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(FIND_CATEGORY_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();
			int idCategory = rs.getInt("id");
			String name = rs.getString("name");

			Category category = new Category(idCategory, name);

			return category;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<Category> allCategories() {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Category> categories = new ArrayList<Category>();
		
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(ALL_CATEGORIES);

			rs = pst.executeQuery();
			while (rs.next()) {

				int idCategory = rs.getInt("id");
				String name = rs.getString("name");

				Category category = new Category(idCategory, name);
				categories.add(category);
			}

			return categories;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
