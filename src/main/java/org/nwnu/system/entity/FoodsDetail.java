package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜品信息
 * </p>
 *
 * @author Answer
 * @since 2018-05-13
 */

@TableName("foods_detail")
public class FoodsDetail implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 所属类别
	 */
	private Integer fid;

	/**
	 * 商铺id
	 */
	private Integer resid;

	/**
	 * 菜品名称
	 */
	private String foodsname;

	/**
	 * 图片
	 */
	private String pic;

	/**
	 * 价格
	 */
	private Float price;

	/**
	 * 特惠价格
	 */
	private Float sprice;

	/**
	 * 菜品描述
	 */
	private String desc;

	/**
	 * 是否热卖
	 */
	private String sellstatus;

	/**
	 * 是否推荐
	 */
	private String hotstatus;

	/**
	 * 其他
	 */
	private String remark;

	/**
	 * 操作员id（餐厅户主）
	 */
	private Integer uid;

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

	public Integer getResid() {
		return resid;
	}

	public void setResid(Integer resid) {
		this.resid = resid;
	}

	public String getFoodsname() {
		return foodsname;
	}

	public void setFoodsname(String foodsname) {
		this.foodsname = foodsname;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getSprice() {
		return sprice;
	}

	public void setSprice(Float sprice) {
		this.sprice = sprice;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSellstatus() {
		return sellstatus;
	}

	public void setSellstatus(String sellstatus) {
		this.sellstatus = sellstatus;
	}

	public String getHotstatus() {
		return hotstatus;
	}

	public void setHotstatus(String hotstatus) {
		this.hotstatus = hotstatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
