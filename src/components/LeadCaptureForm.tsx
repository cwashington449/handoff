import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { CheckCircle, Mail } from 'lucide-react';
import { analytics } from '@/lib/analytics';

interface LeadCaptureFormProps {
  type: 'creator' | 'finisher';
  title?: string;
  description?: string;
  className?: string;
}

export function LeadCaptureForm({ 
  type, 
  title, 
  description, 
  className = '' 
}: LeadCaptureFormProps) {
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [submitted, setSubmitted] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const defaultTitle = type === 'creator' 
    ? 'Join the Waitlist - Creators' 
    : 'Apply to Become a Finisher';
  
  const defaultDescription = type === 'creator'
    ? 'Be the first to know when handoff launches and get exclusive early access.'
    : 'Join our network of vetted developers and work on exciting projects.';

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);

    // Simulate API delay
    await new Promise(resolve => setTimeout(resolve, 1000));

    try {
      // Store in localStorage for now, integrate with backend later
      const leadData = {
        name,
        email,
        type,
        timestamp: Date.now(),
        userAgent: navigator.userAgent,
        referrer: document.referrer
      };

      const existingLeads = JSON.parse(localStorage.getItem(`${type}_leads`) || '[]');
      existingLeads.push(leadData);
      localStorage.setItem(`${type}_leads`, JSON.stringify(existingLeads));

      // Track the lead capture
      analytics.leadCapture(type, email);
      analytics.formSubmit(`${type}_lead_form`, true);

      setSubmitted(true);
    } catch (error) {
      console.error('Error submitting form:', error);
      analytics.formSubmit(`${type}_lead_form`, false);
    } finally {
      setIsSubmitting(false);
    }
  };

  if (submitted) {
    return (
      <Card className={`${className}`}>
        <CardContent className="p-6">
          <div className="text-center">
            <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <CheckCircle className="h-8 w-8 text-green-600" />
            </div>
            <h3 className="text-lg font-semibold text-gray-900 mb-2">
              {type === 'creator' ? "You're on the list!" : "Application received!"}
            </h3>
            <p className="text-gray-600">
              {type === 'creator' 
                ? "We'll notify you as soon as handoff is ready. Thanks for your interest!"
                : "We'll review your application and get back to you within 48 hours."
              }
            </p>
          </div>
        </CardContent>
      </Card>
    );
  }

  return (
    <Card className={`${className}`}>
      <CardHeader>
        <div className="flex items-center space-x-3">
          <div className={`w-10 h-10 rounded-lg flex items-center justify-center ${
            type === 'creator' 
              ? 'bg-blue-100' 
              : 'bg-orange-100'
          }`}>
            <Mail className={`h-5 w-5 ${
              type === 'creator' 
                ? 'text-blue-600' 
                : 'text-orange-600'
            }`} />
          </div>
          <div>
            <CardTitle className="text-xl">{title || defaultTitle}</CardTitle>
          </div>
        </div>
        {description && (
          <p className="text-gray-600 mt-2">{description || defaultDescription}</p>
        )}
      </CardHeader>
      <CardContent>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <Label htmlFor="name">Name</Label>
            <Input
              id="name"
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Your name"
              required
              className="mt-1"
            />
          </div>
          <div>
            <Label htmlFor="email">Email</Label>
            <Input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="your@email.com"
              required
              className="mt-1"
            />
          </div>
          <Button 
            type="submit" 
            className={`w-full ${
              type === 'creator'
                ? 'bg-blue-600 hover:bg-blue-700'
                : 'bg-orange-600 hover:bg-orange-700'
            }`}
            disabled={isSubmitting}
            onClick={() => analytics.buttonClick(`${type}_lead_submit`, 'lead_form')}
          >
            {isSubmitting ? 'Submitting...' : (
              type === 'creator' ? 'Join Waitlist' : 'Apply Now'
            )}
          </Button>
        </form>
      </CardContent>
    </Card>
  );
}