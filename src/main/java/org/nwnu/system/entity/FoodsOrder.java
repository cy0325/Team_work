package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */

@TableName("foods_order")
public class FoodsOrder implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 订单编号
	 */
	private String ordernum;

	/**
	 * 商铺户主id
	 */
	private Integer usid;

	/**
	 * 总金额
	 */
	private Float tmoney;

	/**
	 * 下单时间
	 */
	@TableField(value="update_date")
	private Date updateDate;

	/**
	 * 支付方式
	 */
	private String payway;

	/**
	 * 支付状态操作员id
	 */
	private Integer pid;

	/**
	 * 支付状态操作时间
	 */
	private Date poptime;

	/**
	 * 订单状态
	 */
	private String orderstatus;

	/**
	 * 订单状态操作员id
	 */
	private Integer sid;

	/**
	 * 订单状态操作时间
	 */
	private Date suptime;

	/**
	 * 订单操作员id
	 */
	private Integer uid;

	/**
	 * 订单操作时间
	 */
	private Date optime;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getUsid() {
		return usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public Float getTmoney() {
		return tmoney;
	}

	public void setTmoney(Float tmoney) {
		this.tmoney = tmoney;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getPoptime() {
		return poptime;
	}

	public void setPoptime(Date poptime) {
		this.poptime = poptime;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Date getSuptime() {
		return suptime;
	}

	public void setSuptime(Date suptime) {
		this.suptime = suptime;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getOptime() {
		return optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

}
