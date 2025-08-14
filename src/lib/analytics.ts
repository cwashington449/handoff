// Analytics tracking utility for MVP
export interface AnalyticsEvent {
  eventName: string;
  properties?: Record<string, any>;
  timestamp: number;
  sessionId: string;
}

// Generate a simple session ID
const getSessionId = (): string => {
  let sessionId = localStorage.getItem('session_id');
  if (!sessionId) {
    sessionId = `session_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`;
    localStorage.setItem('session_id', sessionId);
  }
  return sessionId;
};

export const trackEvent = (eventName: string, properties?: Record<string, any>) => {
  const event: AnalyticsEvent = {
    eventName,
    properties,
    timestamp: Date.now(),
    sessionId: getSessionId()
  };

  // Log to console for development
  console.log('Analytics Event:', event);
  
  // Store events locally for MVP
  const events = JSON.parse(localStorage.getItem('analytics_events') || '[]');
  events.push(event);
  localStorage.setItem('analytics_events', JSON.stringify(events));
};

// Common event tracking functions
export const analytics = {
  pageView: (page: string) => trackEvent('page_view', { page }),
  buttonClick: (buttonName: string, location: string) => trackEvent('button_click', { buttonName, location }),
  formSubmit: (formName: string, success: boolean) => trackEvent('form_submit', { formName, success }),
  leadCapture: (type: 'creator' | 'finisher', email: string) => trackEvent('lead_capture', { type, email: email.split('@')[1] }), // Don't store full email
  projectStart: (projectType: string) => trackEvent('project_start', { projectType }),
  sectionView: (sectionName: string) => trackEvent('section_view', { sectionName })
};