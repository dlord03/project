package dlord03.plugin.api.data;

public interface DividendSchedule extends SecurityData, Iterable<Dividend> {

  String getCurrency();

}