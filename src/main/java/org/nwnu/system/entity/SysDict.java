package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统字典
 * </p>
 *
 * @author Answer
 * @since 2017-08-12
 */

@TableName("sys_dict")
public class SysDict implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 字段名称
	 */
	private String dict;

	/**
	 * 字段中文名称
	 */
	private String dictzh;

	/**
	 * 字段显示
	 */
	private String dictname;

	/**
	 * 字段存储值
	 */
	private String dictvalue;

	/**
	 * 显示顺序
	 */
	private Integer seq;

	/**
	 * 操作员id
	 */
	private Integer uid;

	/**
	 * 操作时间
	 */
	private Date update_date;
	
	@TableField(exist = false)
	private String uName;



	public Integer getId() {
		return id;   
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDict() {
		return dict;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

	public String getDictzh() {
		return dictzh;
	}

	public void setDictzh(String dictzh) {
		this.dictzh = dictzh;
	}

	public String getDictname() {
		return dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	public String getDictvalue() {
		return dictvalue;
	}

	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUName(){
		return uName;
	}
	
	public void setUName(String uName){
		this.uName=uName;
	}

}
