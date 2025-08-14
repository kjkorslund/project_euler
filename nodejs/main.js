import pg1 from "./pg1.js";
import {} from "./extensions.js";

// TODO: create structure for developing and running code problems
const problem = "p1"
const startTime = Date.now()
const answer = pg1[problem]();
const endTime = Date.now()
console.log(`Answer for ${problem}: ${answer} (${(endTime-startTime)} ms)`)