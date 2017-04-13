package artRoom.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class ComplaintId implements Serializable {

	private Integer idComplainer;
	private Integer idReceiver;
	private Date date;
	private static final long serialVersionUID = 1L;

	public ComplaintId() {
		// TODO Auto-generated constructor stub
	}

	public ComplaintId(Integer idComplainer, Integer idReceiver) {
		super();
		this.idComplainer = idComplainer;
		this.idReceiver = idReceiver;
		this.date = new Date();
	}
	public ComplaintId(Integer idComplainer, Integer idReceiver,Date date) {
		super();
		this.idComplainer = idComplainer;
		this.idReceiver = idReceiver;
		this.date =date;
	}

	public Integer getIdComplainer() {
		return idComplainer;
	}

	public void setIdComplainer(Integer idComplainer) {
		this.idComplainer = idComplainer;
	}

	public Integer getIdReceiver() {
		return idReceiver;
	}

	public void setIdReceiver(Integer idReceiver) {
		this.idReceiver = idReceiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((idComplainer == null) ? 0 : idComplainer.hashCode());
		result = prime * result + ((idReceiver == null) ? 0 : idReceiver.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplaintId other = (ComplaintId) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idComplainer == null) {
			if (other.idComplainer != null)
				return false;
		} else if (!idComplainer.equals(other.idComplainer))
			return false;
		if (idReceiver == null) {
			if (other.idReceiver != null)
				return false;
		} else if (!idReceiver.equals(other.idReceiver))
			return false;
		return true;
	}

}
