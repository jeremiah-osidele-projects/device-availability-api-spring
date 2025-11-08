CREATE SCHEMA IF NOT EXISTS devices_schema;

CREATE TABLE IF NOT EXISTS devices_schema.devices (
  device_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  asset_id integer UNIQUE,
  make varchar,
  model varchar,
  release_year integer,
  os varchar,
  os_version varchar,
  capabilities jsonb NOT NULL DEFAULT '{}'::jsonb,
  webdriver_config jsonb,
  region varchar DEFAULT 'LON',
  status varchar,
  last_polled timestamp,
  reservation_token uuid,
  rave_config jsonb
);