import * as z from "zod";

const jobSchema = z.object({
  username: z.string(),
  hotelName: z.string(),
  rooms: z.number(),
});

export default jobSchema;
