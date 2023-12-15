public class Race {
  private long time;
  private long record;

  public Race(long time, long record) {
      this.time = time;
      this.record = record;
  }

  public long getTime() {
      return time;
  }

  public long getRecord() {
      return record;
  }
}
