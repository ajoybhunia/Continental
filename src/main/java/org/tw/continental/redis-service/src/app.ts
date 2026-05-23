import { Context, Hono } from "hono";
import { RedisClientType } from "redis";
import jobSchema from "./job_schema.ts";

export const createApp = (client: RedisClientType) => {
  const app = new Hono();

  app.post("/", async (ctx: Context) => {
    try {
      const job = jobSchema.parse(await ctx.req.json());
      await client.lPush("pdf-jobs", JSON.stringify(job));

      return ctx.json({
        success: true,
        message: "job is added",
      });
    } catch {
      return ctx.json({
        success: false,
        message: "job is invalid",
      });
    }
  });

  return app;
};
