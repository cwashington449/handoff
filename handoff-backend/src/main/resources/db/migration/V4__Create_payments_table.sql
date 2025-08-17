-- V4: Create payments table
CREATE TABLE IF NOT EXISTS payments (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    project_id UUID NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    payer_id UUID REFERENCES users(id) ON DELETE SET NULL,
    payee_id UUID REFERENCES users(id) ON DELETE SET NULL,
    amount NUMERIC(10,2) NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    escrow_reference VARCHAR(255),
    metadata JSONB DEFAULT '{}'::jsonb,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    captured_at TIMESTAMPTZ,
    refunded_at TIMESTAMPTZ
);

-- Helpful indexes
CREATE INDEX IF NOT EXISTS idx_pay_project ON payments(project_id);
CREATE INDEX IF NOT EXISTS idx_pay_status ON payments(status);
