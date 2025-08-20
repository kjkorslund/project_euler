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

  forEach(fn) {
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
};

export class Primes {
  static global = new Primes();

  #knownPrimes = [2, 3, 5, 7, 11];
  #knownPrimesSet = new Set(this.#knownPrimes);

  constructor() {}

  isPrime(n) {
    while (this.#knownPrimes.last() < n) {
      this.#generateNextPrime();
    }
    return this.#knownPrimesSet.has(n);
  }

  at(index) {
    while(this.#knownPrimes.length < index+1) {
      this.#generateNextPrime();
    }
    return this.#knownPrimes[index];
  }

  findFirst(fn) {
    let knownMatch = this.#knownPrimes.find(fn);
    if (knownMatch) return knownMatch;

    while(true) {
      let candidate = this.#generateNextPrime();
      if (fn(candidate)) {
        return candidate;
      }
    }
  }

  *sequence() {
    for(let p of this.#knownPrimes) {
      yield(p);
    }
    while(true) {
      yield(this.#generateNextPrime());
    }
  }

  #generateNextPrime() {
    let last = this.#knownPrimes.last()
    let n = last+2;
    while (!this.#isPrimeInternal(n)) {
      n += 2;
    }
    this.#knownPrimes.push(n);
    this.#knownPrimesSet.add(n);
    return n;
  }

  #isPrimeInternal(n) {
    for(let i=0; i<this.#knownPrimes.length; i++) {
      let p = this.#knownPrimes[i];
      if (p*p > n) return true;
      if (n.isMultipleOf(p)) return false;
    }
  }
};