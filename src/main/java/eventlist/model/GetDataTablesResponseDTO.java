package eventlist.model;

/**
 * GetDataTablesResponseDTO encapsulates the response sent to the client for a
 * request for paged events data.
 *
 * Fields must conform to spec: https://datatables.net/manual/server-side
 *
 * @author Kwan
 */
public class GetDataTablesResponseDTO {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private Object[] data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object other) {

        boolean result = false;
        if (other instanceof GetDataTablesResponseDTO) {
            GetDataTablesResponseDTO that = (GetDataTablesResponseDTO) other;
            result = (this.getDraw() == that.getDraw()
                    && this.getRecordsTotal() == that.getRecordsTotal()
                    && this.getRecordsFiltered() == that.getRecordsFiltered());
            
            Object[] thisData = this.getData();
            Object[] thatData = that.getData();
            if (thisData != null && thatData != null) {
                // todo(lkm): implement deep equals
            } else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + getDraw()) + getRecordsFiltered() + getRecordsTotal());
    }
}
