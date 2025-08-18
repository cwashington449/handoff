-- V3: Create project_applications table
CREATE TABLE IF NOT EXISTS project_applications (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    project_id UUID NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    finisher_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL DEFAULT 'SUBMITTED',
    cover_letter TEXT,
    bid_amount NUMERIC(10,2),
    proposed_timeline VARCHAR(100),
    attachments JSONB DEFAULT '[]'::jsonb,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Helpful indexes
CREATE INDEX IF NOT EXISTS idx_app_project ON project_applications(project_id);
CREATE INDEX IF NOT EXISTS idx_app_finisher ON project_applications(finisher_id);
CREATE INDEX IF NOT EXISTS idx_app_status ON project_applications(status);
