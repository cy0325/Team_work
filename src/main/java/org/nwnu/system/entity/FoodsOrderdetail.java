package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单详情
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */

@TableName("foods_orderdetail")
public class FoodsOrderdetail implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 订单id
	 */
	private Integer oid;

	/**
	 * 菜品id
	 */
	private Integer gid;

	/**
	 * 数量
	 */
	private Integer gnum;

	/**
	 * 成交价
	 */
	private Float realprice;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 操作人id
	 */
	private Integer uid;

	/**
	 * 操作日期
	 */
	@TableField(value="update_date")
	private Date updateDate;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getGnum() {
		return gnum;
	}

	public void setGnum(Integer gnum) {
		this.gnum = gnum;
	}

	public Float getRealprice() {
		return realprice;
	}

	public void setRealprice(Float realprice) {
		this.realprice = realprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
