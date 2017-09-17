package hr.member;

/**
 * <h2>public interface <i>IMember</i></h2>
 * <br>
 * Player�� Horse, Result ���� ���� �ٸ� �����͵�; �����8�� �ٷ�� '�� ����� �������̽� �̴�.<br>
 * @author TheSON ^-^v
 * @see
 * {@link Memeber}, {@link Player}, {@link Horse}, {@link Result}
 */
public interface IMember extends Cloneable{
	public abstract String getId();
	public abstract void setId(String id);
	public abstract void setData(String... str);
	public abstract String[] toStrings();
	public abstract Object[] toArray();
	public abstract String[] getColumnName();
	public abstract String[] getFieldName();
	public IMember clone();
	public ClassType getClassType();
	//public Class[] getSchema();
}