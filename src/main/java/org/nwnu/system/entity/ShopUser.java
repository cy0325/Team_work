package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 餐厅管理员
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */

@TableName("shop_user")
public class ShopUser implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 商铺id
	 */
	private Integer resid;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 角色编码
	 */
	private String rolecode;

	/**
	 * 是否启用
	 */
	private String status;

	/**
	 * 描述
	 */
	private String remake;

	/**
	 * 操作员id
	 */
	private Integer uid;

	/**
	 * 操作时间
	 */
	@TableField(value="update_date")
	private Date updateDate;

	@TableField(exist = false)
	private String resUserName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResid() {
		return resid;
	}

	public void setResid(Integer resid) {
		this.resid = resid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
