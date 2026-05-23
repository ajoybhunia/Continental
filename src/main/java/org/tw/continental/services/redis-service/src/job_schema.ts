import * as z from "zod";

const jobSchema = z.object({
  bookingId: z.number(),
  username: z.string(),
  hotelName: z.string(),
  rooms: z.number(),
});

export default jobSchema;
