package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */

@TableName("foods_cart")
public class FoodsCart implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 菜品ID
	 */
	private Integer fid;

	/**
	 * 菜品数量
	 */
	private Integer fnum;

	/**
	 * 是否勾选
	 */
	private Integer checked;

	/**
	 * 操作时间
	 */
	@TableField(value="update_date")
	private Date updateDate;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getFnum() {
		return fnum;
	}

	public void setFnum(Integer fnum) {
		this.fnum = fnum;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
