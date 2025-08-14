export class Range {
  static closed(start, end) {
    return new Range(start, end, 'closed');
  }

  static open(start, end) {
    return new Range(start, end, 'open');
  }

  constructor(start, end, type = 'closed') {
    this.start = start;
    this.end = end;
    this.type = (type == 'closed') ? 'closed' : 'open';
  }

  foreach(fn) {
    if (this.type == 'closed') {
      for(let i = this.start; i <= this.end; i++) {
        fn(i);
      }
    } else {
      for(let i = this.start; i < this.end; i++) {
        fn(i);
      }
    }
  }
}