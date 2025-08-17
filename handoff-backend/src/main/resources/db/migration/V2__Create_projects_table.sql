-- V2: Create projects table and project_required_skills join table
CREATE TABLE IF NOT EXISTS projects (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    creator_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    requirements JSONB NOT NULL DEFAULT '{}'::jsonb,
    budget_min NUMERIC(10,2),
    budget_max NUMERIC(10,2),
    budget_currency VARCHAR(3) DEFAULT 'USD',
    complexity VARCHAR(20),
    estimated_timeline VARCHAR(100),
    status VARCHAR(20) DEFAULT 'DRAFT',
    published_at TIMESTAMPTZ,
    application_deadline TIMESTAMPTZ,
    attachments JSONB DEFAULT '[]'::jsonb,
    view_count INTEGER DEFAULT 0,
    application_count INTEGER DEFAULT 0,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Required skills element collection table
CREATE TABLE IF NOT EXISTS project_required_skills (
    project_id UUID NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    skill VARCHAR(100) NOT NULL,
    PRIMARY KEY (project_id, skill)
);

-- Helpful indexes
CREATE INDEX IF NOT EXISTS idx_projects_creator ON projects(creator_id);
CREATE INDEX IF NOT EXISTS idx_projects_status ON projects(status);
CREATE INDEX IF NOT EXISTS idx_projects_published ON projects(published_at);
