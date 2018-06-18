package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 餐厅
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */

@TableName("shop_list")
public class ShopList implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 商铺名称
	 */
	private String name;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 联系人
	 */
	private String linkman;

	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 菜品类型
	 */
	private Integer fname;

	/**
	 * 商铺logo
	 */
	private String logo;

	/**
	 * 二维码
	 */
	private String pic;


	/**
	 * 开通进度（审核结果）
	 */
	private String starttatus;

	/**
	 * 商铺开通时间
	 */
	private Date startime;

	/**
	 * 审核结果
	 */
	private String reviewreason;

	/**
	 * 关闭原因
	 */
	private String reason;

	/**
	 * 账户是否开通
	 */
	private String startphone;

	/**
	 * 是否启用
	 */
	private String status;

	/**
	 * 商铺介绍
	 */
	private String intro;

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
	private String sysUserName;
	@TableField(exist = false)
	private String foodsnameStr;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getFname() {
		return fname;
	}

	public void setFname(Integer fname) {
		this.fname = fname;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}



	public String getStarttatus() {
		return starttatus;
	}

	public void setStarttatus(String starttatus) {
		this.starttatus = starttatus;
	}

	public Date getStartime() {
		return startime;
	}

	public void setStartime(Date startime) {
		this.startime = startime;
	}

	public String getReviewreason() {
		return reviewreason;
	}

	public void setReviewreason(String reviewreason) {
		this.reviewreason = reviewreason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStartphone() {
		return startphone;
	}

	public void setStartphone(String startphone) {
		this.startphone = startphone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getFoodsnameStr() {
		return foodsnameStr;
	}

	public void setFoodsnameStr(String foodsnameStr) {
		this.foodsnameStr = foodsnameStr;
	}

}
