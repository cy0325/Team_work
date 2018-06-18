package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜品类目
 * </p>
 *
 * @author Answer
 * @since 2017-09-19
 */

@TableName("foods_type")
public class FoodsType implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;


	/**
	 * 类目名称
	 */
	private String name;
	/**
     * 餐厅户主
     */
    //private Integer resuserId;
    @TableField(el = "resuser.id", exist = false)
    //private User resuser;
	/**
	 * 类别图片
	 */
	private String pic;

	/**
	 * 显示顺序
	 */
	private Integer sequence;

	/**
	 * 餐厅id
	 */
	private Integer resid;
	/**
	 * 操作人id
	 */
	private Integer uid;


	/**
	 * 操作时间
	 */
	private Date uptime;



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



	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getResid() {
		return resid;
	}

	public void setResid(Integer resid) {
		this.resid = resid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
	
	@TableField(exist = false)
	private String tpName;



	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}



	



	
}
