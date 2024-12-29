package model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;

public interface ItemMapper {

    @Select("SELECT * FROM items")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "quantity", column = "quantity"),
        @Result(property = "location", column = "location"),
        @Result(property = "price", column = "price"),
        @Result(property = "category", column = "category")
    })
    List<Item> getAllItems();

    @Insert("INSERT INTO items (name, quantity, location, price, category) "
            + "VALUES (#{name}, #{quantity}, #{location}, #{price}, #{category})")
    void addItem(Item item);

    @Update("UPDATE items SET name = #{name}, quantity = #{quantity}, location = #{location}, "
            + "price = #{price}, category = #{category} WHERE id = #{id}")
    void updateItem(Item item);

    @Delete("DELETE FROM items WHERE id = #{id}")
    void deleteItem(int id);
}